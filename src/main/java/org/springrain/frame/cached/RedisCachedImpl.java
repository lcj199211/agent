package org.springrain.frame.cached;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springrain.frame.util.SerializeUtil;

public class RedisCachedImpl implements ICached {
	public RedisCachedImpl() {

	}

	// -1 - never expire
	private int expire = 1800;
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public String deleteCached(final byte[] sessionId) throws Exception {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.del(sessionId);
				return null;
			}
		});
		return null;
	}

	@Override
	public String updateCached(final byte[] key, final byte[] session, final Long expireSec) throws Exception {
		return (String) redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public String doInRedis(final RedisConnection connection) throws DataAccessException {
				connection.set(key, session);
				if (expireSec != null) {
					connection.expire(key, expireSec);
				} else {
					connection.expire(key, expire);
				}
				return new String(key);
			}
		});

	}

	@Override
	public Object getCached(final byte[] sessionId) throws Exception {
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] bs = connection.get(sessionId);
				try {
					if (bs != null) {
						return new String(bs, "utf-8");
					} else {
						return null;
					}

					// return new String(bs,"utf8");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				// return SerializeUtil.unserialize(bs);
			}
		});

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Set<String> getKeys(final byte[] keys) throws Exception {
		return redisTemplate.execute(new RedisCallback<Set>() {
			@Override
			@SuppressWarnings("unchecked")
			public Set doInRedis(RedisConnection connection) throws DataAccessException {
				Set<byte[]> setByte = connection.keys(keys);
				if (setByte == null || setByte.size() < 1) {
					return null;
				}
				Set set = new HashSet();
				for (byte[] key : setByte) {
					byte[] bs = connection.get(key);
					try {
						if (bs != null) {
							set.add(new String(bs, "utf-8"));
						} else {
							// return null;
						}

						// set.add(new String(bs,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					// set.add(SerializeUtil.unserialize(bs));
				}

				return set;

			}
		});
	}

	@Override
	public Set getKeyList(final byte[] keys) throws Exception {
		return redisTemplate.execute(new RedisCallback<Set>() {
			@Override
			@SuppressWarnings("unchecked")
			public Set doInRedis(RedisConnection connection) throws DataAccessException {
				Set<byte[]> setByte = connection.keys(keys);
				return setByte;
			}
		});
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Set getHashKeys(final byte[] key) throws Exception {
		return redisTemplate.execute(new RedisCallback<Set>() {
			@Override
			@SuppressWarnings("unchecked")
			public Set doInRedis(RedisConnection connection) throws DataAccessException {
				Set<byte[]> hKeys = connection.hKeys(key);
				if (hKeys == null || hKeys.size() > 1) {
					return null;
				}
				Set set = new HashSet();
				for (byte[] bs : hKeys) {
					set.add(SerializeUtil.unserialize(bs));
				}
				return set;
			}
		});

	}

	@Override
	public Boolean updateHashCached(final byte[] key, final byte[] mapkey, final byte[] value, Long expire)
			throws Exception {

		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Boolean hSet = connection.hSet(key, mapkey, value);
				return hSet;
			}
		});
	}

	@Override
	public Object getHashCached(final byte[] key, final byte[] mapkey) throws Exception {
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] hGet = connection.hGet(key, mapkey);
				return SerializeUtil.unserialize(hGet);

			}
		});
	}

	@Override
	public Long deleteHashCached(final byte[] key, final byte[] mapkey) throws Exception {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Long hDel = connection.hDel(key, mapkey);
				return hDel;

			}
		});
	}

	@Override
	public Long getHashSize(final byte[] key) throws Exception {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Long len = connection.hLen(key);

				return len;

			}
		});
	}

	@Override
	public Long getDBSize() throws Exception {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Long len = connection.dbSize();

				return len;

			}
		});
	}

	@Override
	public void clearDB() throws Exception {
		redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;

			}
		});
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getHashValues(final byte[] key) throws Exception {
		return redisTemplate.execute(new RedisCallback<List>() {
			@Override
			@SuppressWarnings("unchecked")
			public List doInRedis(RedisConnection connection) throws DataAccessException {
				List<byte[]> hVals = connection.hVals(key);

				if (hVals == null || hVals.size() < 1) {
					return null;
				}
				List list = new ArrayList();

				for (byte[] bs : hVals) {
					try {
						if (bs != null) {
							list.add(new String(bs, "utf8"));
						} else {

						}

						// list.add(new String(bs,"utf8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					// list.add(SerializeUtil.unserialize(bs));
				}
				return list;

			}
		});
	}

}
