class GameController < ApplicationController
  before_filter :ensure_valid_game_id

  def squares
    render :layout => "three"
  end

  def pong
    render :layout => false
  end
end
