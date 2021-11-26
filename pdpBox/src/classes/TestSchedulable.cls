public class TestSchedulable implements Schedulable{

    private AsyncContext context;
    private AsyncContext runNext;

    public TestSchedulable(AsyncContext context){
        this.context = context;
    }

    public TestSchedulable(AsyncContext context, AsyncContext runNext) {
        this.context = context;
        this.runNext = runNext;
    }

    public void execute(SchedulableContext sc) {
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestSchedulable', context.name(), true);
        if (runNext == AsyncContext.QUEUEABLE) {
            for (Integer i = 0; i < 51; i++) {
                System.enqueueJob(new TestQueue(AsyncContext.SCHEDULABLE));
            }
        }
        if (runNext == AsyncContext.BATCHABLE) {
            for (Integer i = 0; i < 51; i++) {
                Database.executeBatch(new TestIterableBatch(AsyncContext.SCHEDULABLE));
            }
        }
        if (runNext == AsyncContext.SCHEDULABLE) {
            for (Integer i = 1; i < 52; i++){
                String cron = TestService.createCronExp(Datetime.now().addMinutes(i));
                InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestQueue', cron, true);
                System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.SCHEDULABLE));
            }
        }
        if (runNext == AsyncContext.FUTURE) {
            for (Integer i = 0; i < 51; i++) {
                TestService.runFuture(AsyncContext.QUEUEABLE.name());
            }
        }
    }
}