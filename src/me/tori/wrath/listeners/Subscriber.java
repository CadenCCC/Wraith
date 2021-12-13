package me.tori.wrath.listeners;

import java.util.Collection;

/**
 * @author <b>7orivorian</b>
 * @version <b>Wrath v1.0.0</b>
 * @since <b>December 12, 2021</b>
 */
public interface Subscriber {

    IListener<?> registerListener(IListener<?> listener);

    Collection<IListener<?>> getListeners();
}