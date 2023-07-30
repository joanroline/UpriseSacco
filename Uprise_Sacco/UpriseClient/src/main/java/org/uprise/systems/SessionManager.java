package org.uprise.systems;

import java.util.HashMap;

import org.uprise.systems.models.User;

public class SessionManager {

  private HashMap<Object, Object> sessions = new HashMap<>();

  public void startSession(User user, Object sessionData) {
    sessions.put(user, sessionData);
  }

  public Object getSessionData(User user) {
    return user;
  }

  public void endSession(User user) {
    sessions.remove(user);
  }
}
