package me.tori.wraith.subscriber;

import me.tori.wraith.listener.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A concrete implementation of {@link ISubscriber} that manages event listeners and their registration.
 * This class provides methods to register single or multiple event listeners and retrieve registered listeners.
 *
 * @author <b><a href="https://github.com/7orivorian">7orivorian</a></b>
 * @see ISubscriber
 * @since <b>1.0.0</b>
 */
public class Subscriber implements ISubscriber {

    private final List<@NotNull Listener<?>> listeners = new ArrayList<>();

    /**
     * Registers a single event listener with this subscriber.
     *
     * @param listener The event listener to register.
     * @param <T>      The type of the listener.
     * @return The registered listener.
     */
    @Override
    public <T extends Listener<?>> T registerListener(@NotNull T listener) {
        listeners.add(listener);
        return listener;
    }

    /**
     * Registers multiple event listeners with this subscriber.
     *
     * @param listeners The event listeners to register.
     * @param <T>       The type of the listeners.
     * @return An array containing the registered listeners.
     */
    @SafeVarargs
    @Override
    public final <T extends Listener<?>> T[] registerListeners(@NotNull T... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
        return listeners;
    }

    /**
     * Retrieves a collection of event listeners registered with this subscriber.
     *
     * @return A collection of registered event listeners.
     */
    @Override
    public Collection<Listener<?>> getListeners() {
        return listeners;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "listeners=" + listeners +
                '}';
    }
}