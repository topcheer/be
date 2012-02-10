package com.brightedu.client.ds;

import java.io.Serializable;


public class Page implements Serializable{

	private static final long serialVersionUID = 316776961655725841L;
	// 分页查询开始记录位置
	private int offset;
	// 分页查看下结束位置
	private int end;
	// 每页显示记录数
	private int length;
	// 查询结果总记录数
	private int count;
	// 当前页码
	private int current;
	// 总共页数
	private int total;

	public Page() {
	}

	/**
	 * 构造函数
	 * 
	 * @param begin
	 * @param length
	 */
	public Page(int offset, int length) {
		this.offset = offset;
		this.length = length;
		this.end = this.offset + this.length;
		this.current = (int) Math.floor((this.offset * 1.0d) / this.length) + 1;
	}

	/**
	 * @param begin
	 * @param length
	 * @param count
	 */
	public Page(int offset, int length, int count) {
		this(offset, length);
		this.count = count;
	}

	/**
	 * @return the begin
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
		if (this.length != 0) {
			this.current = (int) Math.floor((this.offset * 1.0d) / this.length) + 1;
		}
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
		if (this.offset != 0) {
			this.current = (int) Math.floor((this.offset * 1.0d) / this.length) + 1;
		}
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
		this.total = (int) Math.floor((this.count * 1.0d) / this.length);
		if (this.count % this.length != 0) {
			this.total++;
		}
	}

	/**
	 * @return the current
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		if (total == 0) {
			return 1;
		}
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}