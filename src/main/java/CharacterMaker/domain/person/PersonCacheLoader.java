package CharacterMaker.domain.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheLoader;

@Component
public class PersonCacheLoader extends CacheLoader<String,Person>{

    @Autowired
    PersonSerializer personSerializer;

    public Person load(String key) throws Exception {
        return personSerializer.deserialize(key);
    }
}