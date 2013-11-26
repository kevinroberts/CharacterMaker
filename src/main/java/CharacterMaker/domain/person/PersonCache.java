package CharacterMaker.domain.person;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

@Component
public class PersonCache {

    LoadingCache<String, Person> cache;

    @Autowired
    PersonCacheLoader loader;

    @Autowired
    PersonRemovalListener listener;

    public void init(){
        cache = CacheBuilder.newBuilder().
                maximumSize(100).
                expireAfterAccess(10, TimeUnit.SECONDS).
                removalListener(listener).
                build(loader);
    }

    public Person get(String key) throws ExecutionException{
        return cache.get(key);
    }
}
