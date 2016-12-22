package lab.io.rush.cache.redis.mapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 解决Jackson2JsonRedisSerializer反序列化 属性没有合适构造函数的问题
 * Created by cpt72 on 2016/12/13.
 */
public class CustomerObjectMapper extends ObjectMapper {


    @Override
    public <T> T readValue(byte[] src, int offset, int len, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {

/*
        解决Jackson2JsonRedisSerializer反序列化问题
        org.springframework.data.redis.serializer.SerializationException: Could not read JSON:
        Can not construct instance of org.datanucleus.store.types.wrappers.Date:
        no long/Long-argument constructor/factory method to deserialize from Number value (1481464347000)*/

        String str = new String(src);
        str = str.replace("org.datanucleus.store.types.wrappers.Date", "java.util.Date");
        src = str.getBytes();

        return super.readValue(src, offset, len, valueType);
    }
}
