/**
 * Created by user on 11/26/21.
 */

public with sharing class TestQueryLocatorBatch implements Database.Batchable<SObject>, Schedulable{

    public void execute(Database.BatchableContext bc, List<Object> chunk) {
    }

    public void finish(Database.BatchableContext bc) {
    }

    public Database.QueryLocator start(Database.BatchableContext bc) {
        return null;
    }

    public void execute(SchedulableContext sc) {
    }
}