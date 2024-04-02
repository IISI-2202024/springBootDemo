package com.kkk.manager.command;

/**
 * <pre>
 * 		外部介接 command 介面
 * </pre>
 * 
 */
public interface ManagerCommand<Q, R> {
	/**
	 * 執行外部介接 command
	 * 
	 * @param request
	 * @return
	 */
	R execute(Q request) throws Exception;
}
