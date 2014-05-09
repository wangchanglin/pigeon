/**
 * Dianping.com Inc.
 * Copyright (c) 2003-2013 All Rights Reserved.
 */
package com.dianping.pigeon.remoting.invoker;

import java.util.List;

import com.dianping.dpsf.exception.DPSFException;
import com.dianping.pigeon.extension.ExtensionLoader;
import com.dianping.pigeon.remoting.invoker.domain.ConnectInfo;

/**
 * 
 * @author xiangwu
 * @Sep 11, 2013
 * 
 */
public class ClientSelector {

	public static Client selectClient(ConnectInfo connectInfo) {
		List<ClientFactory> clientFactories = ExtensionLoader.getExtensionList(ClientFactory.class);
		for (ClientFactory clientFactory : clientFactories) {
			if (clientFactory.support(connectInfo)) {
				return clientFactory.createClient(connectInfo);
			}
		}
		throw new DPSFException("no available client been created from client factory:" + connectInfo);
	}
}
