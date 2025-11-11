package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import java.io.InputStream;

public interface BaseJsonReader {
    JsonValue parse(FileHandle arg1);

    JsonValue parse(InputStream arg1);
}

