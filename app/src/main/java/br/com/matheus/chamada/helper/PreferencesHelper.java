package br.com.matheus.chamada.helper;

import com.orhanobut.hawk.Hawk;

public class PreferencesHelper {

    public static final String CURRENT_USER = "sp_current_user";
    public static final String FINGERPRINT_CHECKED = "sp_fingerprint_checked";
    public static final String FINGERPRINT_LOGIN_USER = "sp_fingerprint_login_user";
    public static final String ACCESS_TOKEN = "sp_access_token";
    public static final String SINGLE_CHECK = "sp_single_check";
    public static final String DOUBLE_CHECK = "sp_double_check";
    public static final String ALL_CHECK = "sp_all_check";

    public static void logout() {
        boolean fingerprint = Hawk.get(FINGERPRINT_CHECKED, false);
        String login = Hawk.get(FINGERPRINT_LOGIN_USER, "");
        String password = Hawk.get(login);

        Hawk.deleteAll();
        Hawk.put(FINGERPRINT_CHECKED, fingerprint);
        Hawk.put(FINGERPRINT_LOGIN_USER, login);
        Hawk.put(login, password);
    }
}
