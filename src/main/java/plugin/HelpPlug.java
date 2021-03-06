package plugin;

import com.jihuayu.wordcount.WordCount;
import com.jihuayu.wordcount.api.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HelpPlug implements Plugin {
    @Override
    public void getCommandUsage(List<String> info) {
        info.add("tell how to use word count!");
    }

    @Override
    public String getCommandName() {
        return "";
    }

    @Override
    public void doCommand(String[] args) {
        for(String i : WordCount.pluginMap.keySet()){
            if("-".equals(i))continue;
            System.out.println(i+"\n");
            List<String> info = new ArrayList<>();
            WordCount.pluginMap.get(i).getCommandUsage(info);
            for(String j : info){
                System.out.println("\t"+j+"\n");
            }
        }
    }
}
