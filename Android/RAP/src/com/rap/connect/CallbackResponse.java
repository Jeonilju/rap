package com.rap.connect;

import org.apache.http.HttpResponse;

public interface CallbackResponse {
	public abstract void success(HttpResponse httpResponse);
	public abstract void error(Exception e);
}
