
package org.datasphere.mdm.core.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

public interface AsyncExecutor extends Executor {
    <T> CompletableFuture<T> async(Supplier<T> supplier);
    CompletableFuture<Void> async(final Runnable runnable);
}
