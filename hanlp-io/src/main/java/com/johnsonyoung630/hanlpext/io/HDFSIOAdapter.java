package com.johnsonyoung630.hanlpext.io;

import com.hankcs.hanlp.corpus.io.IIOAdapter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * HanLP HDFSIOAdapter
 */
public class HDFSIOAdapter implements IIOAdapter {
    private Configuration conf = new Configuration();

    @Override
    public InputStream open(String path) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(path), conf);
        return fs.open(new Path(path));
    }

    @Override
    public OutputStream create(String path) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(path), conf);
        return fs.create(new Path(path));
    }


}
