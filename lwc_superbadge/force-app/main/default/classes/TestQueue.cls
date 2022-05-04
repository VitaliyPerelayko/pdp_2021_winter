public class TestQueue implements Queueable {

    private AsyncContext context;
    private Set<AsyncContext> runNext;

    public TestQueue(AsyncContext context){
        this(context, new Set<AsyncContext>());
    }

    public TestQueue(AsyncContext context, AsyncContext runNext) {
        this(context, new Set<AsyncContext>{runNext});
    }

    public TestQueue(AsyncContext context, Set<AsyncContext> runNext) {
        this.context = context;
        this.runNext = runNext;
    }

    public void execute(QueueableContext bc) {
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestQueue', context.name(), true);
        if (runNext.contains(AsyncContext.QUEUEABLE)) System.enqueueJob(new TestQueue(AsyncContext.QUEUEABLE));
        if (runNext.contains(AsyncContext.BATCHABLE)) {
            for (Integer i = 0; i < 101; i++) {
                Database.executeBatch(new TestIterableBatch(AsyncContext.QUEUEABLE));
            }
        }
        if (runNext.contains(AsyncContext.SCHEDULABLE)) {
            for (Integer i = 1; i < 10; i++){
                String cron = TestService.createCronExp(Datetime.now().addMinutes(i));
                System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.QUEUEABLE));
            }
        }
        if (runNext.contains(AsyncContext.FUTURE)) {
            for (Integer i = 0; i < 50; i++) {
                TestService.runFuture(AsyncContext.QUEUEABLE.name());
            }
        }
    }
}