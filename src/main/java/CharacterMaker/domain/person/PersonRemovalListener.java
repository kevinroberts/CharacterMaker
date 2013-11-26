package CharacterMaker.domain.person;

import org.springframework.stereotype.Component;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

@Component
public class PersonRemovalListener implements RemovalListener{


    public void onRemoval(RemovalNotification notification) {
        System.out.println("Person associated with the key("+
                notification.getKey()+ ") is removed.");
    }

}