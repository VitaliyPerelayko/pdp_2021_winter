public with sharing class ContactController {

    @AuraEnabled(Cacheable = true)
    public static List<Contact> getContacts() {
//        return [
//                SELECT Id, FirstName, LastName, Email
//                FROM Contact
//                ORDER BY FirstName
//        ];
        throw  new AuraHandledException('error');
    }
}