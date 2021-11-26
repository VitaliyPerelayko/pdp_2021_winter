public class TestIterableBatch implements Database.Batchable<CustomEntity>, Database.Stateful{

    private AsyncContext runNext;
    private AsyncContext context;
    private Integer recordProcessed = 0;

    public TestIterableBatch(AsyncContext context, AsyncContext runNext) {
        this.context = context;
        this.runNext = runNext;
    }

    public TestIterableBatch(AsyncContext context){
        this.context = context;
    }

    public void execute(Database.BatchableContext bc, List<CustomEntity> chunk) {
        if (runNext == AsyncContext.QUEUEABLE) {
            for (Integer i = 0; i < 51; i++) {
                System.enqueueJob(new TestQueue(AsyncContext.BATCHABLE));
            }
        }
        if (runNext == AsyncContext.BATCHABLE) {
            for (Integer i = 0; i < 51; i++) {
                Database.executeBatch(new TestIterableBatch(AsyncContext.BATCHABLE));
            }
        }
        if (runNext == AsyncContext.SCHEDULABLE) {
            for (Integer i = 1; i < 52; i++){
                String cron = TestService.createCronExp(Datetime.now().addMinutes(i));
                System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.BATCHABLE));
            }
        }
        if (runNext == AsyncContext.FUTURE) {
            for (Integer i = 0; i < 2; i++) {
                TestService.runFuture(AsyncContext.BATCHABLE.name());
            }
        }
        if (context != AsyncContext.SYNCHRONOUS) throw new AssertException('Exception');
        recordProcessed += chunk.size();
    }

    public void finish(Database.BatchableContext bc) {
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestIterableBatch', context.name(), true);
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestIterableBatch', 'recordProcessed: ' + recordProcessed, true);
    }

    public Iterable<CustomEntity> start(Database.BatchableContext bc) {
        if (context != AsyncContext.SYNCHRONOUS || runNext != null) return new List<CustomEntity>{new CustomEntity(13)};
        List<CustomEntity> customEntities = new List<CustomEntity>(100000);
        for (Integer i = 0; i < 100000; i++){
            customEntities[i] = new CustomEntity(i);
        }
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestIterableBatch', ' customEntities.size():' +  customEntities.size(), true);
        return customEntities;
    }

    public class CustomEntity{
        private Integer num;

        public CustomEntity(Integer num){
            this.num = num;
        }

        public Integer getNum(){
            return num;
        }
    }
}

