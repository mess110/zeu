class WebController < ApplicationController
  before_filter :server_info

  def index
    @games = @server_info['games'].nil? ? [] : @server_info['games']
  end

  def game_controller
  end

  def reports
    if (params[:id] == "last")
      redirect_to :action => :reports, :id => Report.last.id
      return
    end
    @report = Report.find(params[:id])
    render :xml => @report
  end
end
