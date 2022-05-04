public class InternalMessageManager {

    public enum Type {DEBUG, ERROR}

    public static InternalMessage__c newInternalMessage(Type type, String message, Boolean serialize){
        return newInternalMessage(type, null, message, serialize);
    }

    public static InternalMessage__c newInternalMessage(Type type, String clazz, String message, Boolean serialize){
        InternalMessage__c im = new InternalMessage__c(Type__c = type.name(), Class__c = clazz, Message__c = message);
        if (serialize) insert im;
        return im;
    }
}