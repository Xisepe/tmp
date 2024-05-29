package ru.ccfit.golubevm.musicdbapp.core.repository.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class QueryBuilder {
    @RequiredArgsConstructor
    private enum ClauseStage {
        FROM("find", "find"),
        WHERE("by", "where"),
        ORDER_BY("orderBy", "order by");
        private final String value;
        private final String sqlName;
        public static ClauseStage fromString(String value) {
            return Arrays.stream(ClauseStage.values())
                    .filter(e->e.value.equals(value))
                    .findFirst().orElse(null);
        }
    }

    @RequiredArgsConstructor
    private enum CmpOp {
        EQ("eq","="),
        NOT_EQ("notEq", "<>"),
        LT("lessThan", "<"),
        GR("greaterThan", ">"),
        LIKE("like", "like");

        private final String methodName;
        private final String sqlName;
        public static CmpOp fromString(String methodName) {
            return Arrays.stream(CmpOp.values())
                    .filter(e->e.methodName.equals(methodName))
                    .findFirst().orElse(null);
        }
    }
    @RequiredArgsConstructor
    private enum Sort {
        ASC("asc"), DESC("desc");
        private final String val;
        public static Sort fromString(String methodName) {
            return Arrays.stream(Sort.values())
                    .filter(e->e.val.equals(methodName))
                    .findFirst().orElse(null);
        }
    }

    public String buildQuery(String[] tokens, Class<?> typeClass) {
        if (tokens == null) throw new IllegalArgumentException("Args must be not null");
        if (tokens.length < 2) throw new RuntimeException("Wrong method name. At least should be find_one or find_all");
        if (!tokens[0].equals("find")) throw new RuntimeException("Wrong method name. Should start with find");

        ClauseStage currentStage = ClauseStage.FROM;
        Set<ClauseStage> doneStages = new HashSet<>();
        var query = new StringBuilder();
        buildFrom(typeClass, query);
        boolean first = true;
        for (int i = 1; i < tokens.length - 1; i++) {
            var token = tokens[i];
            if (ClauseStage.fromString(token) != null) {
                doneStages.add(currentStage);
                currentStage = ClauseStage.fromString(token);
                if (doneStages.contains(currentStage)) throw new RuntimeException("wrong method name, several similar entries");
                query.append(currentStage.sqlName).append(' ');
                first = true;
            } else {
                switch (currentStage) {
                    case WHERE -> {
                        var op = CmpOp.fromString(tokens[++i]);
                        if (!first) {
                            query.append(" and ");
                        }
                        query.append(token).append(' ').append(op.sqlName).append(' ').append(':').append(token).append(' ');
                        first = false;
                    }
                    case ORDER_BY -> {
                        var op = Sort.fromString(tokens[++i]);
                        if (!first) {
                            query.append(" , ");
                        }
                        query.append(token).append(' ').append(op.val);
                        first = false;
                    }
                    default -> throw new IllegalStateException("cannot be here if valid");
                }
            }
        }
        return query.toString();
    }

    private void buildFrom(Class<?> typeClass, StringBuilder query) {
        var alias = Character.toLowerCase(typeClass.getSimpleName().charAt(0));
        query.append(String.format("select %s from %s %s ", alias, typeClass.getSimpleName(), alias));
    }
}
