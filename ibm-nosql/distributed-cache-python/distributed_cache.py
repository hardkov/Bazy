import json

from etcd_client import EtcdClient


class DistributedCache(object):
    def __init__(self, config_path, key_prefix, logger):
        self.etcd_client = EtcdClient(config_path, key_prefix, logger, 240)  # reconnect every 4 minutes
        self.logger = logger
        self.key_prefix = key_prefix
        self.cache = {}
        self.etcd_client.add_watch_prefix_callback(self.key_prefix, self.prefix_callback)

    def prefix_callback(self, event):
        print(event)

    def get(self, key: str, use_cache: bool = True) -> str:
        if use_cache and self.cache.get(key):
            return self.cache.get(key)

        full_key = str(self.key_prefix) + "/" + str(key)

        data = self.etcd_client.get(full_key)[0]

        if not data:
            return data

        json_data = json.loads(data.decode("utf8"))
        self.cache[key] = json_data
        return json_data

    def put(self, key: str, value: str):
        full_key = self.key_prefix + "/" + key
        json_value = json.dumps(value)
        self.etcd_client.put(full_key, json_value)

    def delete(self, key: str):
        full_key = self.key_prefix + "/" + key
        self.etcd_client.delete(full_key)
