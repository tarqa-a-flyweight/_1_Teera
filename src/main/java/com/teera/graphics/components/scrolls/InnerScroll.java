package com.teera.graphics.components.scrolls;

import com.teera.chunks.ChunkingStrategy;
import com.teera.chunks.ChunkingStrategyFactory;
import com.teera.chunks.WrapStrategy;
import com.teera.chunks.WrapStrategyFactory;
import com.teera.graphics.components.areas.TextComponent;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;

import static com.teera.debug.Logmas.LOGGER;

public class InnerScroll extends ScrollPane implements Observer
{
    public InnerScroll(String contents)
    {
        ChunkingStrategyFactory chunkingStrategyFactory = ChunkingStrategyFactory.createFactory();
        ChunkingStrategy chunker = chunkingStrategyFactory.createChunkingStrategy();

        Collection<String> s = chunker.chunking(contents);

        WrapStrategyFactory wrapStrategyFactory = WrapStrategyFactory.createFactory();
        WrapStrategy wrapper = wrapStrategyFactory.createWrapStrategy();

        Collection<TextComponent> c = wrapper.wrap(s);

        setNodes(c);
    }

    public String getContents()
    {
        StringBuilder result = new StringBuilder();

        if (getContent() instanceof VBox vBox)
        {
            for (Node child : vBox.getChildren())
            {
                if (child instanceof TextComponent textComponent)
                {
                    result.append(textComponent.contents());
                } else
                {
                    throw new RuntimeException("Узел не является TextComponent!");
                }
            }

        } else
        {
            // рекурсия вплоть до TextComponent
        }
        return result.toString();
    }

    @Override
    public void update(Observable observable)
    {
        ChunkingStrategyFactory chunkerFactory = ChunkingStrategyFactory.createFactory();
        ChunkingStrategy chunker = chunkerFactory.createChunkingStrategy();

        WrapStrategyFactory wrapStrategyFactory = WrapStrategyFactory.createFactory();
        WrapStrategy wrapper = wrapStrategyFactory.createWrapStrategy();

        Collection<String> chunks = chunker.chunking(getContents());

        setNodes(wrapper.wrap(chunks));
    }

    private void setNodes(Collection<TextComponent> components)
    {
        if (components.size() == 1)
        {
            TextComponent c = components.iterator().next();
            if (c instanceof Node node)
            {
                setContent(node);
            } else
            {
                throw new RuntimeException("Текстовый компонент не является узлом!");
            }

            if (c instanceof Observable observable)
            {
                observable.add(this);
            } else
            {
                throw new RuntimeException("Узел не является наблюдаемым!");
            }
            return;
        }

        Collection<Node> nodes = new ArrayList<>();

        for (TextComponent component : components)
        {
            if (component.contents().isEmpty()) continue;

            if (component instanceof Node node)
            {
                nodes.add(node);
            } else
            {
                throw new RuntimeException("Текстовый компонент не является узлом!");
            }

            if (component instanceof Observable observable)
            {
                observable.add(this);
            } else
            {
                throw new RuntimeException("Узел не является наблюдаемым!");
            }
        }

        VBox compose = new VBox(5);
        compose.getChildren().addAll(nodes);
        setContent(compose);
    }
}