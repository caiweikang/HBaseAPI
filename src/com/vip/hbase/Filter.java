package com.vip.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;


/**
 * HBase API ����ǿ����������ʹ��
 * @author Administrator
 *
 */
public class Filter {

	public static void main(String[] args) throws Exception {
		HBaseConfiguration conf = new HBaseConfiguration();
		HTable table = new HTable(conf, "tab1".getBytes());
		Scan scan = new Scan();
		
		// ���˳��м�Ϊrk1������
	//	org.apache.hadoop.hbase.filter.Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator("rk1".getBytes()));
		
		// ����ƥ��
		org.apache.hadoop.hbase.filter.Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator("^.*3$"));
		
		scan.setFilter(filter);
		ResultScanner scanner = table.getScanner(scan);
		Result result = null;
		while((result=scanner.next())!=null) {
			byte[] value = result.getValue("cf1".getBytes(), "c1".getBytes());
			String string = new String(value);
			System.out.println(string);
		}
		
	}

}
