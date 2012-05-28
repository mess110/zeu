class ApiController < ApplicationController
  before_filter :server_info
  before_filter :ensure_valid_game_id, :only => :show

  def index
    render :json => {
      :realtime_server => APP_CONFIG['realtime_host_from_outside'],
      :games => @server_info['games']
    }
  end

  def show
    render :json => @server_info['games'][params['id']]
  end

  def report
    Report.create(:stacktrace => params['stacktrace'])
    render :text => 200
  end

  def ping
    render :json => { 
      :pong  => true,
      :realtime_server => APP_CONFIG['realtime_host_from_outside']
    }
  end
end
