package controllers

import models.Account
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.libs.json.Writes
import play.api.mvc.Action
import play.api.mvc.Controller
import scalaoauth2.provider.AuthInfo
import scalaoauth2.provider.AuthorizationCode
import scalaoauth2.provider.ClientCredentials
import scalaoauth2.provider.OAuth2Provider
import scalaoauth2.provider.OAuth2ProviderActionBuilders.AuthorizedAction
import scalaoauth2.provider.OAuth2ProviderActionBuilders.executionContext
import scalaoauth2.provider.OAuthGrantType
import scalaoauth2.provider.Password
import scalaoauth2.provider.RefreshToken
import scalaoauth2.provider.TokenEndpoint

class OAuthController extends Controller with OAuth2Provider {

  implicit val authInfoWrites = new Writes[AuthInfo[Account]] {
    def writes(authInfo: AuthInfo[Account]) = {
      Json.obj(
        "account" -> Json.obj(
          "email" -> authInfo.user.email),
        "clientId" -> authInfo.clientId,
        "redirectUri" -> authInfo.redirectUri)
    }
  }

  override val tokenEndpoint = new TokenEndpoint {
    override val handlers = Map(
      OAuthGrantType.AUTHORIZATION_CODE -> new AuthorizationCode(),
      OAuthGrantType.REFRESH_TOKEN -> new RefreshToken(),
      OAuthGrantType.CLIENT_CREDENTIALS -> new ClientCredentials(),
      OAuthGrantType.PASSWORD -> new Password())
  }

  def accessToken = Action.async { implicit request =>
    issueAccessToken(new MyDataHandler())
  }

  def resources = AuthorizedAction(new MyDataHandler()) { request =>
    Ok(Json.toJson(request.authInfo))
  }

}