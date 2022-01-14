package hello.advanced.trace;

import lombok.Getter;

import java.util.UUID;

public class TraceId {

    @Getter
    private String id;

    @Getter
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // 너무 길기 때문에 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public Boolean isFirstLevel() {
        return level == 0;
    }
}
