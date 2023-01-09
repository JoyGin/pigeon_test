// Autogenerated from Pigeon (v5.0.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.example.pigeon;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Message {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class Book {
    private @Nullable String title;
    public @Nullable String getTitle() { return title; }
    public void setTitle(@Nullable String setterArg) {
      this.title = setterArg;
    }

    private @Nullable String author;
    public @Nullable String getAuthor() { return author; }
    public void setAuthor(@Nullable String setterArg) {
      this.author = setterArg;
    }

    public static final class Builder {
      private @Nullable String title;
      public @NonNull Builder setTitle(@Nullable String setterArg) {
        this.title = setterArg;
        return this;
      }
      private @Nullable String author;
      public @NonNull Builder setAuthor(@Nullable String setterArg) {
        this.author = setterArg;
        return this;
      }
      public @NonNull Book build() {
        Book pigeonReturn = new Book();
        pigeonReturn.setTitle(title);
        pigeonReturn.setAuthor(author);
        return pigeonReturn;
      }
    }
    @NonNull ArrayList<Object> toList() {
      ArrayList<Object> toListResult = new ArrayList<Object>(2);
      toListResult.add(title);
      toListResult.add(author);
      return toListResult;
    }
    static @NonNull Book fromList(@NonNull ArrayList<Object> list) {
      Book pigeonResult = new Book();
      Object title = list.get(0);
      pigeonResult.setTitle((String)title);
      Object author = list.get(1);
      pigeonResult.setAuthor((String)author);
      return pigeonResult;
    }
  }
  private static class BookApiCodec extends StandardMessageCodec {
    public static final BookApiCodec INSTANCE = new BookApiCodec();
    private BookApiCodec() {}
    @Override
    protected Object readValueOfType(byte type, @NonNull ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return Book.fromList((ArrayList<Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(@NonNull ByteArrayOutputStream stream, Object value)     {
      if (value instanceof Book) {
        stream.write(128);
        writeValue(stream, ((Book) value).toList());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter. */
  public interface BookApi {
    @NonNull List<Book> search(@NonNull String keyword);
    @NonNull List<Book> find(@NonNull String keyword);

    /** The codec used by BookApi. */
    static MessageCodec<Object> getCodec() {
      return       BookApiCodec.INSTANCE;    }
    /**Sets up an instance of `BookApi` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, BookApi api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.BookApi.search", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            ArrayList wrapped = new ArrayList<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              assert args != null;
              String keywordArg = (String)args.get(0);
              if (keywordArg == null) {
                throw new NullPointerException("keywordArg unexpectedly null.");
              }
              List<Book> output = api.search(keywordArg);
              wrapped.add(0, output);
            }
            catch (Error | RuntimeException exception) {
              ArrayList<Object> wrappedError = wrapError(exception);
              wrapped = wrappedError;
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.BookApi.find", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            ArrayList wrapped = new ArrayList<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              assert args != null;
              String keywordArg = (String)args.get(0);
              if (keywordArg == null) {
                throw new NullPointerException("keywordArg unexpectedly null.");
              }
              List<Book> output = api.find(keywordArg);
              wrapped.add(0, output);
            }
            catch (Error | RuntimeException exception) {
              ArrayList<Object> wrappedError = wrapError(exception);
              wrapped = wrappedError;
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  /** Generated class from Pigeon that represents Flutter messages that can be called from Java. */
  public static class ColorApi {
    private final BinaryMessenger binaryMessenger;
    public ColorApi(BinaryMessenger argBinaryMessenger){
      this.binaryMessenger = argBinaryMessenger;
    }
    public interface Reply<T> {
      void reply(T reply);
    }
    /** The codec used by ColorApi. */
    static MessageCodec<Object> getCodec() {
      return       new StandardMessageCodec();
    }
    public void updateColor(@NonNull Long colorArg, Reply<Long> callback) {
      BasicMessageChannel<Object> channel =
          new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.ColorApi.updateColor", getCodec());
      channel.send(new ArrayList<Object>(Collections.singletonList(colorArg)), channelReply -> {
        @SuppressWarnings("ConstantConditions")
        Long output = channelReply == null ? null : ((Number)channelReply).longValue();
        callback.reply(output);
      });
    }
  }
  @NonNull private static ArrayList<Object> wrapError(@NonNull Throwable exception) {
    ArrayList<Object> errorList = new ArrayList<>(3);
    errorList.add(exception.toString());
    errorList.add(exception.getClass().getSimpleName());
    errorList.add("Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    return errorList;
  }
}
