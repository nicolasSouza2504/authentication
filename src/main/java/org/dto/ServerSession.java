package org.dto;

public class ServerSession {

    private static final ThreadLocal<Session> sessionThread = new ThreadLocal();

    public Session getSession() {
        return sessionThread.get();
    }

    public void setSession(Session session) {
        sessionThread.set(session);
    }

}
