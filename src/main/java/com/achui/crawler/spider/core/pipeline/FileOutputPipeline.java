package com.achui.crawler.spider.core.pipeline;

import com.achui.crawler.spider.OutputPipelineException;
import com.achui.crawler.spider.core.RequestItem;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 文件输出
 * @author portz
 * @date 2020/8/15 16:42
 */
@Slf4j
public class FileOutputPipeline implements OutputPipeline {
    private File outputFile;
    private String outputFilePath;

    public FileOutputPipeline(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.outputFile = new File(outputFilePath);
    }

    @Override
    @SneakyThrows
    public void output(RequestItem requestItem) throws OutputPipelineException {
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        String content = "";
        Map<String, String> item = requestItem.getItem();
        for (Map.Entry<String, String> entry : item.entrySet()) {
            content += String.format("key:%s, value:%s%n", entry.getKey(), entry.getValue());

        }
        FileUtils.write(outputFile, content, Charset.defaultCharset(), true);
    }
}
