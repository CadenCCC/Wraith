/*
 * Copyright (c) 2024 7orivorian.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package dev.tori.example.statusevents;

import dev.tori.wraith.bus.EventBus;
import dev.tori.wraith.bus.IEventBus;
import dev.tori.wraith.event.Target;
import dev.tori.wraith.event.status.StatusEvent;
import dev.tori.wraith.listener.LambdaEventListener;
import dev.tori.wraith.subscriber.Subscriber;

/**
 * @author <a href="https://github.com/7orivorian">7orivorian</a>
 * @since 3.2.0
 */
public class TerminationExample {

    private static final IEventBus EVENT_BUS = new EventBus();
    private static final Subscriber SUBSCRIBER = new Subscriber() {{
        registerListeners(
                new LambdaEventListener<StringEvent>(Target.fine(StringEvent.class), 1, event -> {
                    event.message = "Hello world!";
                    event.terminate();
                }),
                new LambdaEventListener<StringEvent>(Target.fine(StringEvent.class), 0, event -> {
                    event.message = "I do not greet";
                })
        );
    }};

    public static void main(String[] args) {
        EVENT_BUS.subscribe(SUBSCRIBER);

        StringEvent event = new StringEvent(null);
        System.out.println(EVENT_BUS.dispatch(event)); // This will print "true" because the event was terminated

        // Terminated events are not handled by subsequent listeners
        System.out.println(event.message); // This will print "Hello world!"
    }

    private static class StringEvent extends StatusEvent {

        public String message;

        public StringEvent(String message) {
            this.message = message;
        }
    }
}