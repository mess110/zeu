require 'sinatra'

get '/' do
  erb :index
end

get '/bb' do
  erb :bb
end
