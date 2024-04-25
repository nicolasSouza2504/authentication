package org.dto;

public class ServerSession {

    private static final ThreadLocal<Session> sessionThread = new ThreadLocal();

    public static Session getSession() {
        return sessionThread.get();
    }

    public static void setSession(Session session) {
        sessionThread.set(session);
    }

}
