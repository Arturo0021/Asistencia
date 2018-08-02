package mensajes.team.mx.asistencia;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class Instance_Token extends FirebaseInstanceIdService {
    String Token = "";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        Token = FirebaseInstanceId.getInstance().getToken();

        Log.i("#", Token);

    }
}
