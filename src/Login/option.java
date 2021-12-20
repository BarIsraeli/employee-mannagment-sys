package Login;

public enum option {
    Admin, Worker,Consultant,Developer;

    option() {
    }

    public static option fromvalue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }
}

