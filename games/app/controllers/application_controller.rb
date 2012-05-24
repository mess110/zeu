class ApplicationController < ActionController::Base
  protect_from_forgery

  def ensure_valid_game_id
    server_info = ServerWrapper.info
    if !server_info['games'].keys.include? params[:id]
      render :text => "game id invalid"
    end
  end

  def server_info
    @server_info = ServerWrapper.info
  end
end
