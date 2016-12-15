package main.java.Managers;

import main.java.Intefaces.INameResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NameResolverDemo implements INameResolver {
    private final String name = "name";
    private final String age = "age";
    private final String language = "language";
    private final String strongLanguage = "strongLanguage";
    private final String serversGroup = "serversGroup";
    private final String serverName = "serverName";
    private final String voiceChat = "voiceChat";
    private final String chatListening = "chatListening";
    private final String chatSpeaking = "chatSpeaking";
    private final String chatType = "chatType";
    private final String pvp = "pvp";
    private final String pve = "pve";
    private final String createDate = "createDate";
    private final String updateDate = "updateDate";
    private Map<String, String> dictionary;
    private VocabularyManager vocabularyManager;

    @Autowired
    public NameResolverDemo(VocabularyManager vocabularyManager) {
        this.dictionary = new HashMap<>();
        this.dictionary.put(name, name);
        this.dictionary.put(age, age);
        this.dictionary.put(pve, pve);
        this.dictionary.put(pvp, pvp);
        this.dictionary.put(language, language);
        this.dictionary.put(strongLanguage, "strong_language");
        this.dictionary.put(serversGroup, "servers_group");
        this.dictionary.put(serverName, "server_name");
        this.dictionary.put(voiceChat, "voice_chat");
        this.dictionary.put(chatListening, "chat_listening");
        this.dictionary.put(chatSpeaking, "chat_speaking");
        this.dictionary.put(chatType, "chat_type");
        this.dictionary.put(createDate, "create_date");
        this.dictionary.put(updateDate, "update_date");
        this.vocabularyManager = vocabularyManager;

    }

    @Override
    public String getDAOTypeName(String nameDTO) {
        return this.dictionary.get(nameDTO);
    }

    @Override
    public String getDAOValueName(String typeDTO, String nameDTO) {
        return this.vocabularyManager.getIdByTypeAndValue(typeDTO, nameDTO);
    }
}
