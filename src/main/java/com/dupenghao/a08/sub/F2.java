package com.dupenghao.a08.sub;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * proxyMode=ScopeProxyMode.TARGET_CLASS 代表在注入到spring中的context时就注入的时代理对象
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class F2 {
}
