package plugin;

import com.jihuayu.WordCount;
import com.jihuayu.core.api.Plugin;

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
    public void loadCommand(String[] args) {
        for(String i : WordCount.pluginMap.keySet()){
            if(i.equals("-"))continue;
            System.out.println(i+"\n");
            List<String> info = new ArrayList<>();
            WordCount.pluginMap.get(i).getCommandUsage(info);
            for(String j : info){
                System.out.println("\t"+j+"\n");
            }
        }
    }
}
