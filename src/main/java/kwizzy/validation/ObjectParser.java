package kwizzy.validation;

import kwizzy.validation.annot.CustomBinding;
import kwizzy.validation.exceptions.TransferFormException;
import kwizzy.validation.impl.Form;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

import static org.reflections.ReflectionUtils.*;

public class ObjectParser<T> {

    private Class<T> cl;
    private Form form;
    private Validator validator;

    public ObjectParser(Class<T> cl, Form form, Validator validator) {
        this.cl = cl;
        this.form = form;
        this.validator = validator;
    }

    public T get() throws TransferFormException {

        Object instance = invokeConstructor();
        setNormalFields(instance);
        setCustomBindingFields(instance);
        return (T) instance;
    }

    private void setCustomBindingFields(Object instance) throws TransferFormException {
        for (Field field : getFields(cl, withAnnotation(CustomBinding.class))) {
            field.setAccessible(true);
            CustomBinding cb = field.getAnnotation(CustomBinding.class);
            String fnName = cb.value();
            Set<Method> methods = getAllMethods(cl, withName(fnName), withParameters(Form.class), withReturnType(field.getType()));
            if (methods.isEmpty())
                throw new TransferFormException(String.format("Field %s has custom binding method but can't found " +
                        "method call %s.", field.getName(), fnName));
            Method m = methods.iterator().next();
            m.setAccessible(true);
            try {
                Object ret = m.invoke(instance, form);
                field.set(instance, ret);
            } catch (Exception e) {
                throw new TransferFormException(String.format("Unable to call method %s or setting in field %s.", fnName,
                        field));
            }
        }
    }

    private void setNormalFields(Object instance) throws TransferFormException {
        for (String field : validator.getRules().keySet()) {
            Set<Field> fields = getFields(cl, withName(field));
            if (fields.isEmpty())
                continue;
            Field classField = fields.iterator().next();
            classField.setAccessible(true);
            Class<?> type = classField.getType();
            Object valueFromType = getValueFromType(type, field);
            try {
                classField.set(instance, valueFromType);
            } catch (IllegalAccessException e) {
                throw new TransferFormException("Can't set value for field " + field + ".");
            }
        }
    }

    private Object getValueFromType(Class<?> type, String field) throws TransferFormException{
        Optional obj = Optional.empty();
        if (type == Integer.class || type == int.class)
            obj = form.getInt(field);
        else if (type == Boolean.class || type == boolean.class)
            obj = form.getBool(field);
        else if (type == Double.class || type == double.class)
            obj = form.getDouble(field);
        else if (type == Float.class || type == float.class)
            obj = form.getFloat(field);
        else if (type == Byte.class || type == byte.class)
            obj = form.getByte(field);
        else if (type == Character.class || type == char.class)
            obj = form.getChar(field);
        else if (type == Long.class || type == long.class)
            obj = form.getLong(field);
        else if (type == String.class)
            obj = form.getString(field);
        else if (type.isEnum())
        {
            if (form.getInt(field).isPresent())
                obj = Optional.ofNullable(type.getEnumConstants()[form.getInt(field).get()]);
            else if (form.getString(field).isPresent() && !obj.isPresent())
                obj = Optional.ofNullable(Enum.valueOf((Class<? extends Enum>)type, form.getString(field).get()));
        }
        if (obj.isPresent())
            return obj.get();
        else
            return null;
    }

    private Object invokeConstructor() throws TransferFormException {
        Set<Constructor> constructors = getConstructors(cl, withParametersCount(0));
        if (constructors.isEmpty())
            throw new TransferFormException("No default constructor available with 0 parameters.");
        Constructor constructor = constructors.iterator().next();
        constructor.setAccessible(true);
        try {
            return constructor.newInstance();
        } catch (Exception e) {
            throw new TransferFormException("Error when invoking the constructor.");
        }
    }
}
