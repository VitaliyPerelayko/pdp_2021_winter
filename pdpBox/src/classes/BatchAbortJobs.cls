public class BatchAbortJobs implements Database.Batchable<SObject> {

    public static final String DEFAULT_QUERY = 'SELECT Id, CronJobDetailId FROM CronTrigger WHERE CronJobDetailId IN (SELECT Id FROM CronJobDetail WHERE Name LIKE \'TestScheduleJob%\')';

    private String query;

    public BatchAbortJobs(String query) {
        this.query = query;
    }

    public BatchAbortJobs() {
        this.query = DEFAULT_QUERY;
    }

    public void execute(Database.BatchableContext param1, List<SObject> param2) {
        for (SObject obf : param2) {
            System.abortJob(obf.Id);
        }
    }

    public void finish(Database.BatchableContext param1) {
    }

    public Database.QueryLocator start(Database.BatchableContext param1) {
        return Database.getQueryLocator(query);

    }
}