package com.company.game.ai;

import com.company.game.GameWorld;
import com.company.game.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeNode
{
    private GameWorld world;
    private int value;
    private boolean isMax;
    private List<TreeNode> children;

    public TreeNode(GameWorld world,boolean isMax)
    {
        children = new ArrayList<>();
        this.world = world;
        this.isMax = isMax;

        calcValue();
    }

    public List<TreeNode> getChildren()
    {
        return children;
    }

    private void calcValue()
    {
        if(!world.isGameOver())
        {
            generateChildren();
            if(isMax)
            {
                value = children.stream().max(Comparator.comparing(TreeNode::getValue)).get().getValue();
            }
            else
            {
                value = children.stream().min(Comparator.comparing(TreeNode::getValue)).get().getValue();
            }
        }
        else
        {
            if(world.getWinner() == Player.TIE)
            {
                value = 0;
            }
            if(world.getWinner() == Player.ONE)
            {
                value = -1;
            }
            if(world.getWinner() == Player.TWO)
            {
                value = 1;
            }
        }
    }

    private void generateChildren()
    {
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                Player currentPlayer = isMax ? Player.TWO: Player.ONE;
                if (world.getPlayerInSquare(x,y) == Player.NONE)
                {
                    children.add(new TreeNode(world.setPlayerMove(x,y,currentPlayer),!isMax));
                }
            }
        }
    }

    public TreeNode getChildMatchingGameWorld(GameWorld worldToMatch)
    {
        for(TreeNode t : children)
        {
            if(t.world.equals(worldToMatch))
            {
                return t;
            }
        }
        return null;
    }

    public int getValue()
    {
        return value;
    }

    public GameWorld getWorld()
    {
        return world;
    }
}
