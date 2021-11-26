public class TestIterableBatch implements Database.Batchable<CustomEntity>, Database.Stateful{

    private AsyncContext context;

    public TestIterableBatch(AsyncContext context){
        this.context = context;
    }

    public void execute(Database.BatchableContext bc, List<CustomEntity> chunk) {
    }

    public void finish(Database.BatchableContext bc) {
        InternalMessageManager.newInternalMessage(InternalMessageManager.Type.DEBUG, 'TestIterableBatch', context.name(), true);
    }

    public Iterable<CustomEntity> start(Database.BatchableContext bc) {
        if (context != AsyncContext.SYNCHRONOUS) return new List<CustomEntity>{new CustomEntity()};
        return new List<CustomEntity>{new CustomEntity()};
    }

    public class CustomEntity{

    }
}

