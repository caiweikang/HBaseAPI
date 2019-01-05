package com.vip.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hdfs.DFSClient.Conf;

public class HBaseDemo {
	public static void main(String[] args) throws Exception {
		HBaseConfiguration conf = new HBaseConfiguration();
		conf.set("hbase.zookeeper.quorum", "192.168.154.129:2181,192.168.154.130:2181,192.168.154.132:2181");
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		// create table name
		TableName tname = TableName.valueOf("tab1");
		HTableDescriptor desc = new HTableDescriptor(tname);
		
		// create column family
		HColumnDescriptor cf1 = new HColumnDescriptor("cf1".getBytes());
		cf1.setMaxVersions(3);
		desc.addFamily(cf1);
		HColumnDescriptor cf2 = new HColumnDescriptor("cf2".getBytes());
		cf1.setMaxVersions(3);
		desc.addFamily(cf2);
		
		// create table
		admin.createTable(desc);
		
		// put method
		HTable table = new HTable(conf, "tab1".getBytes());
		Put put = new Put("rk1".getBytes());
		put.add("cf1".getBytes(), "c1".getBytes(), "value1".getBytes());
		table.put(put);
		
		// get method°¢ delete method¿‡À∆
		table.close();
		// …æ≥˝±Ì
		admin.deleteTable("tab1".getBytes());
		admin.close();
	}


	
	
}
