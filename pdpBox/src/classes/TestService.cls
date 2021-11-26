public class TestService {

    @Future
    public static void futureFutureRun(){
        runFuture(AsyncContext.FUTURE.name());
    }

    @Future
    public static void runFuture(String context){
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestService - rubFuture', context, true);
    }

    @Future
    public static void createAccountCopy(){
        Account acc = [SELECT Name, BillingCity FROM Account LIMIT 1];
        acc.Id = null;
        acc.Name += 'Copy';
        acc.BillingCity += 'Copy';
        insert acc;
    }

    @Future
    public static void runQueue(){
        System.enqueueJob(new TestQueue(AsyncContext.FUTURE));
    }

    @Future
    public static void runBatch(){
        Database.executeBatch(new TestIterableBatch(AsyncContext.FUTURE));
    }

    @Future
    public static void scheduleJobs(){
        for (Integer i = 1; i < 102; i++){
            String cron = createCronExp(Datetime.now().addMinutes(i));
            InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestService - scheduleJobs', cron, true);
            System.schedule('TestScheduleJob' + cron, cron, new TestSchedulable(AsyncContext.FUTURE));
        }
    }

    public static String createCronExp(Datetime dt){
        return ('' + dt.second() + ' ' + dt.minute() + ' ' + dt.hour() + ' ' + dt.day() + ' ' + dt.month() + ' ? ' + dt.year());
    }
}