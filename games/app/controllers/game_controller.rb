class GameController < ApplicationController

  before_filter :ensure_valid_game_id

  def squares
    render :layout => "three"
  end

  def ensure_valid_game_id
    server_info = ServerWrapper.info
    if !server_info['games'].keys.include? params[:id]
      render :text => "game id invalid"
    end
  end
end
