public class TestQueue implements Queueable {

    private AsyncContext context;
    private AsyncContext runNext;

    public TestQueue(AsyncContext context){
        this(context, null);
    }

    public TestQueue(AsyncContext context, AsyncContext runNext) {
        this.context = context;
        this.runNext = runNext;
    }

    public void execute(QueueableContext bc) {
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestQueue', context.name(), true);
        if (runNext == AsyncContext.QUEUEABLE) System.enqueueJob(new TestQueue(AsyncContext.QUEUEABLE));
        if (runNext == AsyncContext.BATCHABLE) {
            Database.executeBatch(new TestIterableBatch(AsyncContext.QUEUEABLE));
            Database.executeBatch(new TestIterableBatch(AsyncContext.QUEUEABLE));
        }
        if (runNext == AsyncContext.SCHEDULABLE) {
            for (Integer i = 1; i < 10; i++){
                String cron = TestService.createCronExp(Datetime.now().addMinutes(i));
                InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestQueue', cron, true);
                System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.QUEUEABLE));
            }
        }
        if (runNext == AsyncContext.FUTURE) {
            for (Integer i = 0; i < 51; i++) {
                TestService.runFuture(AsyncContext.QUEUEABLE.name());
            }
        }
    }
}