package com.company.game.ai;

import com.company.game.GameState;
import com.company.game.GameWorld;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GameWorldTree
{
    private TreeNode root;
    private DifficultyLevel level;
    private Random gen;


    public  GameWorldTree(DifficultyLevel difficultyLevel)
    {
        root = new TreeNode(GameWorld.empty(),false);
        level = difficultyLevel;
        gen = new Random();
    }

    public void setNewRoot(GameWorld newWorld)
    {
        root = root.getChildMatchingGameWorld(newWorld);
    }

    public GameWorld getChildGameWorld()
    {
        List<TreeNode> children = root.getChildren();
        GameWorld optimalWorld = children.stream().max(Comparator.comparing(TreeNode::getValue)).get().getWorld();
        switch (level)
        {
            case EASY:
                if(gen.nextFloat() >= 0.1)
                    return children.get(gen.nextInt(children.size())).getWorld();
                break;
            case MEDIUM:
                if(gen.nextFloat() >= 0.5)
                    return children.get(gen.nextInt(children.size())).getWorld();
                break;
        }
        return optimalWorld;
    }
}
