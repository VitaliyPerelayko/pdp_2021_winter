/**
 * Created by user on 11/26/21.
 */

public with sharing class TestQueryLocatorBatch implements Database.Batchable<SObject>, Schedulable{

    private AsyncContext runNext;
    private AsyncContext context;
    private String method;

    public TestQueryLocatorBatch(AsyncContext context, AsyncContext runNext, String method) {
        this.context = context;
        this.runNext = runNext;
        this.method = method;
    }

    public TestQueryLocatorBatch(AsyncContext context, AsyncContext runNext) {
        this.context = context;
        this.runNext = runNext;
    }

    public TestQueryLocatorBatch(AsyncContext context){
        this.context = context;
    }

    public void execute(Database.BatchableContext bc, List<Object> chunk) {
        if (method.contains('execute')) {
            if (runNext == AsyncContext.FUTURE) TestService.runFuture(AsyncContext.BATCHABLE.name());
            if (runNext == AsyncContext.QUEUEABLE) {
                for (Integer i = 0; i < 1; i++) {
                    System.enqueueJob(new TestQueue(AsyncContext.BATCHABLE));
                }
            }
            if (runNext == AsyncContext.BATCHABLE) {
                for (Integer i = 0; i < 101; i++) {
                    Database.executeBatch(new TestIterableBatch(AsyncContext.BATCHABLE));
                }
            }
            if (runNext == AsyncContext.SCHEDULABLE) {
                for (Integer i = 1; i <= 101; i++){
                    String cron = TestService.createCronExp(Datetime.now().addSeconds(i*5));
                    System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.BATCHABLE));
                }
            }
        }
    }

    public void finish(Database.BatchableContext bc) {
        if (method.contains('finish')) {
            if (runNext == AsyncContext.FUTURE) TestService.runFuture(AsyncContext.BATCHABLE.name());
            if (runNext == AsyncContext.QUEUEABLE) {
                for (Integer i = 0; i < 1; i++) {
                    System.enqueueJob(new TestQueue(AsyncContext.BATCHABLE));
                }
            }
            if (runNext == AsyncContext.BATCHABLE) {
                for (Integer i = 0; i < 5; i++) {
                    Database.executeBatch(new TestIterableBatch(AsyncContext.BATCHABLE));
                }
            }
            if (runNext == AsyncContext.SCHEDULABLE) {
                for (Integer i = 1; i <= 10; i++){
                    String cron = TestService.createCronExp(Datetime.now().addSeconds(i*5));
                    System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.BATCHABLE));
                }
            }
        }
    }

    public Database.QueryLocator start(Database.BatchableContext bc) {
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestIterableBatch', context.name(), true);
        if (method.contains('start')) {
            if (runNext == AsyncContext.FUTURE) TestService.runFuture(AsyncContext.BATCHABLE.name());
            if (runNext == AsyncContext.QUEUEABLE) {
                for (Integer i = 0; i < 1; i++) {
                    System.enqueueJob(new TestQueue(AsyncContext.BATCHABLE));
                }
            }
            if (runNext == AsyncContext.BATCHABLE) {
                for (Integer i = 0; i < 101; i++) {
                    Database.executeBatch(new TestIterableBatch(AsyncContext.BATCHABLE));
                }
            }
            if (runNext == AsyncContext.SCHEDULABLE) {
                for (Integer i = 1; i <= 11; i++){
                    String cron = TestService.createCronExp(Datetime.now().addSeconds(i*5));
                    System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.BATCHABLE));
                }
            }
        }
        return Database.getQueryLocator([SELECT Id FROM Account LIMIT 1]);
    }

    public void execute(SchedulableContext sc) {
    }
}